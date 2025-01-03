package kr.co.sonystore.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.sonystore.mappers.TodaySaleMapper;
import kr.co.sonystore.models.TodaySale;
import kr.co.sonystore.services.TodaySaleService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class TodaySaleServiceImpl implements TodaySaleService {

    @Autowired
    private TodaySaleMapper todaySaleMapper;
    

    @Override
    public TodaySale addItem() throws Exception {
        int result = 0;

        try {
            result = todaySaleMapper.countYesterdaySale();
            
            if (result > 0) {
                todaySaleMapper.insert();
            } else {
                todaySaleMapper.insertZero();
                log.debug("매출이 없습니다.");
            }
        } catch(Exception e) {
            log.error("일별 매출 추가에 실패했습니다.", e);
            throw e;
        }
        
        return todaySaleMapper.selectItem();
    }


    @Override
    public List<TodaySale> getList(int day) throws Exception {
        day *= -1;
        List<TodaySale> output = null;
        try {
            output = todaySaleMapper.selectList(day);
        } catch(Exception e){
            log.error("일별 매출 조회에 실패했습니다.", e);
            throw e;
        }
        
        return output;
    }
    
}
