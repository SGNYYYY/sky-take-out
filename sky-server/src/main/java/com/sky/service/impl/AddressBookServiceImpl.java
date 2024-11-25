package com.sky.service.impl;

import com.sky.constant.MessageConstant;
import com.sky.context.BaseContext;
import com.sky.entity.AddressBook;
import com.sky.mapper.AddressBookMapper;
import com.sky.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AddressBookServiceImpl implements AddressBookService {

    private static final Integer DEFAULT=1;
    private static final Integer NO_DEFAULT=0;

    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     * 新增地址
     *
     * @param addressBook
     */
    public void add(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBookMapper.insert(addressBook);
    }

    /**
     * 条件查询
     * @param addressBook
     * @return
     */
    public List<AddressBook> list(AddressBook addressBook) {
        return addressBookMapper.select(addressBook);
    }

    /**
     * 设置默认地址
     *
     * @param addressBook
     */
    public void setDefault(AddressBook addressBook) {
        //将当前用户的所有地址修改为非默认地址
        addressBook.setUserId(BaseContext.getCurrentId());
        addressBook.setIsDefault(NO_DEFAULT);
        addressBookMapper.updateIsDefaultByUserId(addressBook);

        addressBook.setIsDefault(DEFAULT);
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据id查询地址
     *
     * @param id
     * @return
     */
    public AddressBook getById(Long id) {
        return addressBookMapper.getById(id);
    }

    /**
     * 根据id修改地址
     *
     * @param addressBook
     */
    public void update(AddressBook addressBook) {
        addressBookMapper.update(addressBook);
    }

    /**
     * 根据id删除地址
     *
     * @param id
     */
    public void delete(Long id) {
        addressBookMapper.delete(id);
    }


}
