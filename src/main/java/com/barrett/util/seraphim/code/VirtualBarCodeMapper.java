package src.main.java.com.barrett.util.seraphim.code;
import com.barrett.util.seraphim.temp.VirtualBarCode;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.seraphim.common.base.BaseMapping;
import com.seraphim.common.pojo.Page;
import org.apache.ibatis.annotations.Param;

public interface VirtualBarCodeMapper extends BaseMapping {

     List<VirtualBarCode> selectVirtualBarCodeList (Page page);

     int insertVirtualBarCode(VirtualBarCode record);

     int updateVirtualBarCode(VirtualBarCode record);

     VirtualBarCode getVirtualBarCodeById(Long rowId);

     VirtualBarCode getUpdateRecordById(Long rowId);

     int delVirtualBarCodeByIds(Long[] rowIds);

     int insertBatch(@Param("parentRowId") Long parentRowId, @Param("list") List list);

}