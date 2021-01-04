package com.github.songxchn.common.bean;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SignatureHeader implements Serializable {
    private static final long serialVersionUID = -1223980089856375422L;

    private String timestamp;

    private String nonce;

    private String signature;

    private String serialNo;
}
