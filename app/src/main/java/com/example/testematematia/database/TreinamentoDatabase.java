package com.example.testematematia.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.testematematia.database.dao.DaoTreinandoRoom;
import com.example.testematematia.model.Treinando;

@Database(entities = Treinando.class, version = 2, exportSchema = false)
public abstract class TreinamentoDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DE_DADOS = "treinamento.db";

    public abstract DaoTreinandoRoom getDaoTreinandoRoom();


    public static  TreinamentoDatabase getInstance(Context context) {
        return  Room.
                databaseBuilder(context , TreinamentoDatabase.class, NOME_BANCO_DE_DADOS)
                .allowMainThreadQueries()
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("CREATE TABLE IF NOT EXISTS `Treinando_novo` " +
                                "(`id` INTEGER NOT NULL, `nome` TEXT," +
                                " `ttlMultiplicacao` INTEGER NOT NULL, " +
                                "`ttldivisao` INTEGER NOT NULL, " +
                                "`ttlErro` INTEGER NOT NULL, " +
                                "`ttlAcerto` INTEGER NOT NULL," +
                                " PRIMARY KEY(`id`))");

                        database.execSQL("INSERT INTO Treinando_novo(id, nome, ttlMultiplicacao, ttldivisao, ttlErro, ttlAcerto)" +
                                "SELECT id, nome, ttlMultiplicacao, 0, 0, 0  FROM Treinando");


                        database.execSQL("DROP TABLE Treinando");

                        database.execSQL("ALTER TABLE Treinando_novo RENAME TO Treinando");
                    }
                })
                .build();

    }
}

