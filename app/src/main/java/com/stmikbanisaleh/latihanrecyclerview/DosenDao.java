package com.stmikbanisaleh.latihanrecyclerview;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DosenDao {
    @Query("SELECT * FROM dosen")
    List<Dosen> getAll();

    @Query("SELECT * FROM dosen WHERE id = :id")
    Dosen findById(int id);

    @Query("SELECT * FROM dosen WHERE nama LIKE :nama")
    List<Dosen> findByNama(String nama);

    @Insert
    void insertAll(Dosen ...dosen);

    @Insert
    long insert(Dosen dosen);

    @Update
    void update(Dosen dosen);

    @Delete
    void delete(Dosen dosen);

    @Delete
    void delete(Dosen ...dosen);

//    long insert(Dosen dosen);
}
