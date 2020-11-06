package com.barrett.util.PLC.conf;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * 传送带工位单元
 * 
 * @author shigui.yu
 * @date Aug 10, 2016 11:05:29 AM
 * 
 */
public class TransferUnit extends BaseUnit {

    /**
     * 
     */
    private static final long serialVersionUID = 7027823689435003339L;

    private static final int ID_WIDTH = 24;
    private static final int ID_HEIGHT = 14;
    private static final int STATUS_WIDTH = 14;
    private static final int STATUS_HEIGHT = 14;

    private String sn;
    private int status;

    /**
     * 占用的寄存器开始地址
     */
    private int startPos;

    private JLabel lblId;
    private JLabel lblSn;
    private JLabel lblStatus;

    public TransferUnit(String id, int startPos, Rectangle rectangle) {
        super(id, rectangle);
        this.startPos = startPos;
    }

    @Override
    protected void initUI(Rectangle rectangle) {
        setBackground(new Color(227, 254, 201));
        lblId = new JLabel(id.substring(2), SwingConstants.CENTER);
        lblId.setBounds(rectangle.width - ID_WIDTH, rectangle.height - ID_HEIGHT, ID_WIDTH, ID_HEIGHT);
        lblId.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblId.setToolTipText(id.substring(2));
        add(lblId);

        lblStatus = new JLabel("", SwingConstants.CENTER);
        lblStatus.setBounds(0, 0, STATUS_WIDTH, STATUS_HEIGHT);
        lblStatus.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        add(lblStatus);

        lblSn = new JLabel();
        if (rectangle.width > rectangle.height) {
            lblSn.setVerticalAlignment(SwingConstants.TOP);
        } else {
            lblSn.setVerticalAlignment(SwingConstants.CENTER);
        }
        lblSn.setHorizontalAlignment(SwingConstants.CENTER);
        lblSn.setBounds(0, 0, rectangle.width, rectangle.height);
        add(lblSn);

        //初始化数据
        //        String saveSn = RuntimeDataDao.runtimeDataMap.get(id + "_sn");
        //        if (!StringUtils.isEmpty(saveSn)) {
        //            refreshSn(saveSn);
        //        }
    }

    public void refreshSn(String sn) {
        this.sn = sn;
        this.lblSn.setText(sn);
        this.setToolTipText(sn);
        //保存数据
        saveData(sn, 1);
    }

    public void refreshStatus(int status) {
        switch (status) {
        case 0:
            this.lblStatus.setOpaque(false);
            this.lblStatus.setBackground(null);
            break;
        case 1:
            this.lblStatus.setOpaque(true);
            this.lblStatus.setBackground(Color.GREEN);
            break;
        case 2:
            this.lblStatus.setOpaque(true);
            this.lblStatus.setBackground(Color.RED);
            break;
        }
        this.status = status;
    }

    public String getSn() {
        return this.sn;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getStatus() {
        return this.status;
    }

}
