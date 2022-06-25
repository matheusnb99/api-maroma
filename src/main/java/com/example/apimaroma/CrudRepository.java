package com.example.apimaroma;

import com.google.cloud.Timestamp;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

public interface CrudRepository<T, ID> {

    <S extends T> S save(S entity);

    Optional<T> findById(ID primaryKey) throws ExecutionException, InterruptedException;

    Iterable<T> findAll() throws ExecutionException, InterruptedException;

    long count();

    Timestamp delete(T entity) throws ExecutionException, InterruptedException;

    boolean existsById(ID primaryKey);
}
