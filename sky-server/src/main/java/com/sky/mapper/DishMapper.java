package com.sky.mapper;

import com.sky.entity.Dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品
     * @param categoryId
     * @return
     */
    @Select("select count(*) from dish where category_id=#{categoryId}")
    Integer countByCategoryId(Long categoryId);
}
