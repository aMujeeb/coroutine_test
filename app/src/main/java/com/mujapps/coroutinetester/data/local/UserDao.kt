package com.mujapps.coroutinetester.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mujapps.coroutinetester.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user WHERE username = :uname")
    suspend fun getUser(uname: String): User

    @Query("DELETE FROM user WHERE id = :uid")
    suspend fun deleteUser(uid: Long)
}