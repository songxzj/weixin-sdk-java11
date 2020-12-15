package com.github.sung.wxpay.v3.bean.result.inner;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuditDetail implements Serializable {
    private static final long serialVersionUID = 4909218873339164626L;

    /**
     * 字段名
     * field
     * string
     * 否
     */
    @SerializedName("field")
    private String field;


    /**
     * 字段名称
     * field_name
     * string
     * 否
     */
    @SerializedName("field_name")
    private String fieldName;

    /**
     * 驳回原因
     * reject_reason
     * string
     * 否
     */
    @SerializedName("reject_reason")
    private String rejectReason;

}
