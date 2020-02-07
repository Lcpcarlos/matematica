package com.example.testematematia.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testematematia.model.Treinando;

import java.util.List;

@Dao
public interface DaoTreinandoRoom {
        @Insert
        void salva(Treinando jogador);

        @Delete
        void remove(Treinando jogador);

        @Query("SELECT * FROM Treinando ")
        List<Treinando> todos();

        @Query("SELECT * FROM Treinando where id = :idTreinando")
        Treinando  consultaPontual(int idTreinando);

        @Query("DELETE  FROM Treinando")
        void limpaBaseJogador();

        @Update
        void edita(Treinando treinando);
}
