package GenerateBillNo;

import java.text.DecimalFormat;

public class GenerateBillNoTest {

  @Override
  public String getBillNo(SysSequenceType trade) {
    String tradeType = trade + "";
        // 如果是开发环境，生成规则增加"_DEV_"(非正式)，防止会与测试流水号重复
        tradeType = trade + "_FZS_";

    }
    String sequenceName = tradeType + DateUtil2.getCurrentDateStr(DateUtil2.C_DATA_PATTON_YYYYMMDD);
    SysSequence sysSequence = sysSequenceService.findByName(sequenceName);
    if (sysSequence == null) {
      sysSequence = new SysSequence(sequenceName, 1, MAX_SEQUENCE_VALUE, 1);
      sysSequenceService.register(sysSequence);
    }
    DecimalFormat decimalformat = new DecimalFormat(SEQUENCE_FORMAT);
    return sequenceName + decimalformat.format(sysSequenceService.next(sequenceName));
  }
}
