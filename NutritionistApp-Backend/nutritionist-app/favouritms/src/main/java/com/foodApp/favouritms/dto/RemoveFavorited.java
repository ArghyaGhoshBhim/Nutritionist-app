package com.foodApp.favouritms.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class RemoveFavorited {
    @NotBlank
    @Length(min = 2, max = 15)
    private String username;
    @Min(1)
    private long fdcId;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getFdcId() {
        return fdcId;
    }

    public void setFdcId(long fdcId) {
        this.fdcId = fdcId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemoveFavorited)) return false;
        RemoveFavorited that = (RemoveFavorited) o;
        return getFdcId() == that.getFdcId() && Objects.equals(getUsername(), that.getUsername());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getFdcId());
    }

}
