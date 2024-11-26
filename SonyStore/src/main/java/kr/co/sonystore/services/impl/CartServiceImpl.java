package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.CartMapper;
import kr.co.sonystore.models.Cart;
import kr.co.sonystore.services.CartService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    
    @Override
    public int addOrEditItem(Cart input) throws Exception {
    
        int rows = 0;
        // Cart output = null; 

        try {
            rows = cartMapper.selectCount(input);
            log.debug("rows : " + rows);

            if (rows == 0) {
                rows = cartMapper.insert(input);
                if(rows == 0) {
                    throw new Exception("저장된 데이터가 없습니다.");
                }
            } else {
                rows = cartMapper.updateCount(input);
                if(rows == 0) {
                    throw new Exception("변경된 데이터가 없습니다.");
                }
            }  
        } catch (Exception e) {
            log.error("데이터 저장에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }


    @Override
    public int editItem(Cart input) throws Exception {
        int rows = 0;

        try {
            rows = cartMapper.update(input);

            if (rows == 0) {
                throw new Exception("변경된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 변경에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }


    @Override
    public int deleteItem(Cart input) throws Exception {
        int rows = 0;

        try {
            rows = cartMapper.delete(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

/* 
    @Override
    public Cart getItem(Cart input) throws Exception {
        Cart output = null;

        try {
            output = cartMapper.selectItem(input);

            if (output == null) {
                throw new Exception("조회된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("교수 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }
 */

    @Override
    public List<Cart> getList(Cart input) throws Exception {
        
        List<Cart> output = null;

        try {
            output = cartMapper.selectList(input);
        } catch (Exception e) {
            log.error("장바구니 목록 조회에 실패했습니다.", e);
            throw e;
        }

        return output;
    }


    @Override
    public int deleteList(List<Integer> input) throws Exception {
        int rows = 0;

        try {
            rows = cartMapper.deleteList(input);

            if (rows == 0) {
                throw new Exception("삭제된 데이터가 없습니다.");
            }
        } catch (Exception e) {
            log.error("데이터 삭제에 실패했습니다.", e);
            throw e;
        }

        return rows;
    }

    
}