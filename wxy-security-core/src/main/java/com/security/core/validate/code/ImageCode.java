package com.security.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {

    private BufferedImage image;

    private String code;

    private LocalDateTime expiredTime;

    public ImageCode(BufferedImage image, String code, int expiredTime) {
        this.image = image;
        this.code = code;
        this.expiredTime = LocalDateTime.now().plusSeconds(expiredTime);
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expiredTime) {
        this.image = image;
        this.code = code;
        this.expiredTime = expiredTime;
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expiredTime);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }
}
