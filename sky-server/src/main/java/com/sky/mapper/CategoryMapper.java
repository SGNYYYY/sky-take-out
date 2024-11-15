package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(name, type, sort, status, create_time, update_time, create_user, update_user)" +
            "values(#{name}, #{type}, #{sort}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value= OperationType.INSERT)
    void insert(Category category);

    /**
     * 更新分类
     * @param category
     */
    @AutoFill(value= OperationType.UPDATE)
    void update(Category category);

    /**
     * 分类分页查询
     * @param categoryPageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据类型查询分类
     * @param category
     * @return
     */
    List<Category> list(Category category);

    /**
     * 根据id删除分类
     * @param id
     */
    @Delete("delete from category where id=#{id}")
    void delete(Long id);

    /**
     * 根据id查询分类名称
     * @param id
     */
    @Select("select name from category where id=#{id}")
    String getCategoryNameById(Long id);
}
