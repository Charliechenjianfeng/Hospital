package com.educational.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.common.Constant;
import com.educational.demo.dao.PhotoMapper;
import com.educational.demo.exception.BadRequestException;
import com.educational.demo.model.Photo;
import com.educational.demo.query.PhotoQuery;
import com.educational.demo.service.PhotoService;
import com.educational.demo.util.FileUtil;
import com.educational.demo.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@CacheConfig(cacheNames = "photo")
public class PhotoServiceImpl implements PhotoService {

    @Value("${file.path}")
    private String path;

    @Value("${file.maxSize}")
    private long maxSize;

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    @Cacheable
    public List<Photo> listAll() {
        QueryWrapper<Photo> wrapper = new QueryWrapper<>();
        wrapper.select(Photo.Table.URL,Photo.Table.DESCRIPTION).orderByAsc(Photo.Table.SORT).eq(Photo.Table.DISPLAY, Constant.DISPLAY);
        return photoMapper.selectList(wrapper);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id, String imgPath) {
        String img = path+imgPath;
        FileUtil.del(img);
        photoMapper.deleteById(id);
    }

    @Override
    @Cacheable
    public Photo getById(Long id) {
        return photoMapper.selectById(id);
    }

    @Override
    public void upload(MultipartFile file) {
        FileUtil.checkSize(maxSize, file.getSize());
        File file1 = FileUtil.upload(file, path);
        if (ObjectUtil.isNull(file1)) {
            throw new BadRequestException("上传失败");
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void removeByIdList(List<Long> idList) {
        photoMapper.deleteBatchIds(idList);
    }

    @Override
    @CacheEvict(allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    public void saveOfUpdate(Photo photo) {
        if (photo.getId() == null) {
            photoMapper.insert(photo);
        } else {
            photoMapper.updateById(photo);
        }
    }

    @Override
    @Cacheable
    public Page<Photo> listTableByPage(Integer current, Integer size, PhotoQuery photoQuery) {
        Page<Photo> page = new Page<>(current, size);
        QueryWrapper<Photo> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(photoQuery.getDescription())) {
            wrapper.like(Photo.Table.DESCRIPTION, photoQuery.getDescription());
        }
        if (photoQuery.getStartDate() != null && photoQuery.getEndDate() != null) {
            wrapper.between(Photo.Table.CREATE_TIME, photoQuery.getStartDate(), photoQuery.getEndDate());
        }
        if (photoQuery.getDisplay() != null) {
            wrapper.eq(Photo.Table.DISPLAY, photoQuery.getDisplay());
        }
        return photoMapper.selectPage(page, wrapper);
    }
}
