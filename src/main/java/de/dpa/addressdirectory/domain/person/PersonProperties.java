package de.dpa.addressdirectory.domain.person;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Denys Babich
 * 16.09.2024
 */
@ConfigurationProperties("addressdirectory.person")
public class PersonProperties {

    private int pageSize = 10;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
