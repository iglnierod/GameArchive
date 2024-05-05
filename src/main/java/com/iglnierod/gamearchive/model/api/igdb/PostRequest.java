/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iglnierod.gamearchive.model.api.igdb;

public class PostRequest {

    private StringBuilder fields = new StringBuilder("fields ");
    private StringBuilder where = new StringBuilder("where ");
    private StringBuilder exclude = new StringBuilder("exclude ");
    private StringBuilder sort = new StringBuilder("sort ");
    private StringBuilder search = new StringBuilder("search ");
    private StringBuilder limit = new StringBuilder("limit ");
    private StringBuilder offset = new StringBuilder("offset ");
    StringBuilder[] varArray;

    private PostRequest() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public String asString() {
        // "fields ... ; where ...; limit ...;"
        StringBuilder sb = new StringBuilder();

        if (fields.length() > 7) { // Verifica si se ha modificado fields
            sb.append(fields);
        }
        if (search.length() > 7) { // Verifica si se ha modificado search
            sb.append(search);
        }
        if (where.length() > 6) { // Verifica si se ha modificado where
            sb.append(where);
        }
        if (exclude.length() > 8) { // Verifica si se ha modificado exclude
            sb.append(exclude);
        }
        if (sort.length() > 5) { // Verifica si se ha modificado sort
            sb.append(sort);
        }

        if (limit.length() > 6) { // Verifica si se ha modificado limit
            sb.append(limit);
        }
        if (offset.length() > 7) { // Verifica si se ha modificado offset
            sb.append(offset);
        }

        return sb.toString();
    }

    public static class Builder {

        private PostRequest postRequest;

        private Builder() {
            postRequest = new PostRequest();
        }

        public Builder fields(String fields) {
            postRequest.fields.append(fields).append(";");
            return this;
        }

        public Builder where(String where) {
            if (where.isEmpty()) {
                return this;
            }
            postRequest.where.append(where).append(";");
            return this;
        }

        public Builder exclude(String exclude) {
            postRequest.exclude.append(exclude).append(";");
            return this;
        }

        public Builder sort(String sort) {
            postRequest.sort.append(sort).append(";");
            return this;
        }

        public Builder search(String search) {
            postRequest.search.append(search).append(";");
            return this;
        }

        public Builder limit(String limit) {
            postRequest.limit.append(limit).append(";");
            return this;
        }

        public Builder offset(String offset) {
            postRequest.offset.append(offset).append(";");
            return this;
        }

        public PostRequest build() {
            return postRequest;
        }
    }
}
