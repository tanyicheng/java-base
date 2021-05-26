package src.main.java.com.barrett.util.seraphim.code;
import com.barrett.util.seraphim.temp.Heartbeat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seraphim.common.pojo.BaseListBO;
import com.seraphim.common.pojo.Page;
import src.main.java.com.barrett.util.seraphim.code.HeartbeatMapper;

@Service
public class HeartbeatServiceImpl implements HeartbeatService {

    @Autowired
    private HeartbeatMapper heartbeatMapper;

    //查询所有记录
    @Override
    public BaseListBO searchHeartbeatList(Heartbeat form) {
        Page page = Page.getInstance(form);
        List<Heartbeat> list = heartbeatMapper.selectHeartbeatList(page);
        return BaseListBO.getInstance(page, list);
    }

    //查询记录详情
    @Override
    public Heartbeat getHeartbeatById(Long id) {
        Heartbeat record = heartbeatMapper.getHeartbeatById(id);
        return record;
    }

    //获取编辑对象
    @Override
    public Heartbeat getUpdateRecordById(Long id) {
        Heartbeat record = heartbeatMapper.getUpdateRecordById(id);
        return record;
    }

    //添加记录
    @Override
    public void insertHeartbeat(Heartbeat form) {
        heartbeatMapper.insertHeartbeat(form);
    }

    //修改记录
    @Override
    public void updateHeartbeat(Heartbeat form) {
        heartbeatMapper.updateHeartbeat(form);
    }

    //批量删除记录
    @Override
    public void deleteHeartbeat(Long[] ids) {
        heartbeatMapper.delHeartbeatByIds(ids);
    }

}