package com.educational.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.educational.demo.model.Photo;
import com.educational.demo.query.PhotoQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    /**
     * 前台分页查询所有照片
     *
     * @return 照片分页
     */
    List<Photo> listAll();

    /**
     * 后台分页查询所有照片
     *
     * @param current    当前页
     * @param size       页面大小
     * @param photoQuery 查询条件
     * @return 照片分页
     */
    Page<Photo> listTableByPage(Integer current, Integer size, PhotoQuery photoQuery);

    /**
     * 根据ID删除照片
     *
     * @param id 照片ID
     */
    void removeById(Long id,String imgPath);

    /**
     * 根据ID列表批量删除照片
     *
     * @param idList 照片ID列表
     */
    void removeByIdList(List<Long> idList);

    /**
     * 保存或更新照片
     *
     * @param photo 照片
     */
    void saveOfUpdate(Photo photo);

    /**
     * 根据ID查询照片
     *
     * @param id 照片ID
     */
    Photo getById(Long id);

    /**
     * 上传图片
     * @param file
     */
    void upload(MultipartFile file);

}