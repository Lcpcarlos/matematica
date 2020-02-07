package com.example.testematematia.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testematematia.database.dao.DaoTreinandoRoom;
import com.example.testematematia.model.Treinando;

@Database(entities = Treinando.class, version = 1, exportSchema = false)
public abstract class TreinamentoDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "treinamento.db";

    public abstract DaoTreinandoRoom getDaoTreinandoRoom();


    public static  TreinamentoDatabase getInstance(Context context) {
        return  Room.
                databaseBuilder(context , TreinamentoDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

    }
}

