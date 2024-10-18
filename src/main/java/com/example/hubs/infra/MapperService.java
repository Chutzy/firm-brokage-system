package com.example.hubs.infra;

public interface MapperService {

    <D> D map(Object source, Class<D> destination);

    /*<S,D> S map(S source, Class<D> destination);

    void map(Object source, Object destination);*/
}
