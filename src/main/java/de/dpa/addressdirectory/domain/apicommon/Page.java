package de.dpa.addressdirectory.domain.apicommon;

import java.util.List;

/**
 * @author Denys Babich
 * 16.09.2024
 */
public class Page<T> {

    private List<T> items;

    private int number;

    private int totalPages;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
