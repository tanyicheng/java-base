package src.main.java.com.barrett.util.seraphim.code;
import com.barrett.util.seraphim.temp.Heartbeat;
import java.util.List;
import com.seraphim.common.base.BaseMapping;
import com.seraphim.common.pojo.Page;
import org.springframework.stereotype.Service;

public interface HeartbeatService {

    BaseListBO searchHeartbeatList (Heartbeat form);

     void insertHeartbeat(Heartbeat record);

     void updateHeartbeat(Heartbeat record);

     Heartbeat getHeartbeatById(Long rowId);

     Heartbeat getUpdateRecordById(Long rowId);

     void deleteHeartbeat(Long[] rowIds);

}