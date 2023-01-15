package com.stf.service;

import com.stf.entity.Author;
import com.stf.entity.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService{

    /**
     * @Transactional
     * 事务管理需要在service层中使用
     * 作用：
     *  1.若要更新数据库则需要添加@Transactional注解
     *  2.当一个service层中的方法对多条数据进行操作，但其中一条数据操作失败则会回滚到使用这条方法前的数据库状态
     *  3.Transactional事务结束Hibernate session也跟着关闭，ManyToMany关系会因为事务关闭无法加载LList中的数据(看本project当中TopicServiceImp的findTopicById)
     */

    /**
     * 更新或添加(PUT,POST)一个对象
     *  当调用Repository中的save方法，并传入对象:
     *      1.如果该传入的对象包含id则更新该数据
     *      2.如果该传入的对象不包含id则新增一条数据
     */

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    @Override
    public Author creatAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Author> findAllAuthor() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public List<Author> findByNickNameLick(String nickName) {
        return authorRepository.findByNickNameLike(nickName);
    }

    @Transactional
    @Override
    public Page<Author> findAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
