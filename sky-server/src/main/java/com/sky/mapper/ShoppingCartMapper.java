package com.sky.mapper;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {


    /**
     * 条件查询购物车商品
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> list(ShoppingCart shoppingCart);

    /**
     * 修改购物车
     * @param shoppingCart
     */
    @Update("update shopping_cart set number = #{number} where id = #{id} ")
    void updateNumberById(ShoppingCart shoppingCart);

    /**
     * 添加购物车
     * @param shoppingCart
     */
    @Insert("insert into shopping_cart (name, image, user_id, dish_id, setmeal_id, dish_flavor, number, amount, create_time) " +
            "values (#{name}, #{image}, #{userId}, #{dishId}, #{setmealId}, #{dishFlavor}, #{number}, #{amount}, #{createTime} )")
    void insert(ShoppingCart shoppingCart);

    /**
     * 根据id删除购物车商品
     */
    @Delete("delete from shopping_cart where id = #{id}")
    void deleteById(Long id);

    /**
     * 根据用户id删除商品
     * @param userId
     */
    @Delete("delete from shopping_cart where user_id = #{userId} ")
    void deleteByUserId(Long userId);

}