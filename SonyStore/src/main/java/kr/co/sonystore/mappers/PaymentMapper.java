package kr.co.sonystore.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.sonystore.models.Payment;


@Mapper
public interface PaymentMapper {

    /**
     * 결제내역에 결제정보를 추가한다
     * @param input - 입력할 결제내역 정보에 대한 모델 객체
     * @return 추가된 데이터 수
     */
    @Insert(
        "INSERT INTO payments ( \n" +
            "status, memberid, totalcount, total, \n" +
            "payoption, insertdate, paycheck ) \n" +
        "VALUES ( \n" +
            "'결제대기', #{memberid}, #{totalcount}, #{total}, \n" +
            "'신용카드', NOW(), 'N' )"
         )
    @Options(useGeneratedKeys = true, keyProperty = "payid", keyColumn = "payid")
    public int insert(Payment input);


    /**
     * 결제내역의 결제정보를 수정한다
     * @param input - 수정할 결제내역 정보에 대한 모델 객체
     * @return 수정된 데이터 수
     */
    @Update(
        "UPDATE payments SET\n" +
            "date = NOW(), \n" +
            "status = '결제완료', \n" +
            "ordername = #{ordername}, \n" +
            "orderemail = #{orderemail},\n" +
            "orderphone = #{orderphone},\n" +
            "receivername = #{receivername},\n" +
            "receiverphone = #{receiverphone},\n" +
            "postcode = #{postcode},\n" +
            "addr1 = #{addr1},\n" +
            "addr2 = #{addr2},\n" +
            "request = #{request},\n" +
            "dlvdate = #{dlvdate},\n" +
            "payoption = #{payoption},\n" +
            "paycheck = 'Y'\n" +
        "WHERE payid = #{payid}"
    )
    public int update(Payment input);


    /**
     * 결제내역을 단일 조회한다
     * @param input - 조회할 결제내역 일련번호를 가진 모델 객체
     * @return 조회된 데이터
     */
    @Select(
        "SELECT \n" +
            "payid, date, status, \n" +
            "memberid, ordername, orderemail, orderphone, \n" +
            "receivername, receiverphone, \n" + 
            "postcode, addr1, addr2, \n" + 
            "request, dlvdate, \n" + 
            "totalcount , total,  payoption, insertdate, paycheck\n" +
        "FROM payments \n" + 
        "WHERE payid = #{payid} AND paycheck = 'Y'"
    )
    @Results(id="paymentMap", value={
        @Result(property="payid", column="payid"),
        @Result(property="date", column="date"),
        @Result(property="status", column="status"),
        @Result(property="memberid", column="memberid"),
        @Result(property="ordername", column="ordername"),
        @Result(property="orderemail", column="orderemail"),
        @Result(property="orderphone", column="orderphone"),
        @Result(property="receivername", column="receivername"),
        @Result(property="receiverphone", column="receiverphone"),
        @Result(property="postcode", column="postcode"),
        @Result(property="addr1", column="addr1"),
        @Result(property="addr2", column="addr2"),
        @Result(property="request", column="request"),
        @Result(property="dlvdate", column="dlvdate"),
        @Result(property="totalcount", column="totalcount"),
        @Result(property="total", column="total"),
        @Result(property="payoption", column="payoption"),
        @Result(property="insertdate", column="insertdate"),
        @Result(property="paycheck", column="paycheck")
    })
    public Payment selectItem(Payment input);

    
    /**
     * 결제내역 중 최근 배송지를 5개까지 조회한다
     * @param input - 조회할 결제내역 정보에 대한 모델 객체
     * @return 조회된 데이터 목록
     */
    @Select(
        "SELECT DISTINCT \n" +
        	"receivername, receiverphone, postcode, addr1, addr2, date \n" +
        "FROM payments \n" +
        "WHERE memberid = #{memberid} AND paycheck = 'Y'\n" +
        "ORDER BY date DESC \n" +
        "LIMIT 5"
    )
    public List<Payment> selectDlvList(Payment input);


    /**
     * 결제내역을 삭제한다
     * @param input - 삭제할 결제내역 정보에 대한 모델 객체
     * @return 삭제된 데이터 수
     */
    @Delete(
        "DELETE FROM payments \n" + //
            "WHERE paycheck = 'N' AND \n" + //
                "insertdate < DATE_ADD(NOW(), interval -1 minute)"
    )
    public int delete(Payment input);
}
