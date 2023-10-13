package br.com.andlucsil.electriccarapp.data.local

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import br.com.andlucsil.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_BATERIA
import br.com.andlucsil.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_CAR_ID
import br.com.andlucsil.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_POTENCIA
import br.com.andlucsil.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_PRECO
import br.com.andlucsil.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_RECARGA
import br.com.andlucsil.electriccarapp.data.local.CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO
import br.com.andlucsil.electriccarapp.domain.Carro

class CarRepository(private val context: Context) {

    fun save(carro: Carro) : Boolean {
        var isSaved = false
        try {
            findCarById(1)
            val db = CarsDbHelper(context).writableDatabase
            val values = ContentValues().apply {
                put(CarrosContract.CarEntry.COLUMN_NAME_CAR_ID, carro.id)
                put(CarrosContract.CarEntry.COLUMN_NAME_PRECO, carro.preco)
                put(CarrosContract.CarEntry.COLUMN_NAME_BATERIA, carro.bateria)
                put(CarrosContract.CarEntry.COLUMN_NAME_POTENCIA, carro.potencia)
                put(CarrosContract.CarEntry.COLUMN_NAME_RECARGA, carro.recarga)
                put(CarrosContract.CarEntry.COLUMN_NAME_URL_PHOTO, carro.urlPhoto)
            }
            val inserted = db?.insert(CarrosContract.CarEntry.TABLE_NAME, null, values)
            if (inserted != null) {
                isSaved = true
            }
        } catch (ex: Exception) {
            ex.message?.let { Log.e("Erro ao inserir -> ", it) }
        }
        return isSaved
    }

    fun findCarById(id: Int) : Carro? {
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(BaseColumns._ID,
                                COLUMN_NAME_CAR_ID,
                                COLUMN_NAME_PRECO,
                                COLUMN_NAME_BATERIA,
                                COLUMN_NAME_POTENCIA,
                                COLUMN_NAME_RECARGA,
                                COLUMN_NAME_URL_PHOTO)

        val filter = "$COLUMN_NAME_CAR_ID = ?"
        val filterValues = arrayOf(id.toString())
        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME,
            columns,
            filter,
            filterValues,
            null,
            null,
            null
        )
        var itemId: Long = 0
        var preco = ""
        var bateria = ""
        var potencia = ""
        var recarga = ""
        var urlPhoto = ""
        with(cursor) {
            while (moveToNext()) {
                itemId = getLong(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ", itemId.toString())
                preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                bateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA))
                potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                recarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA))
                urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))
            }
        }
        cursor.close()
        return Carro(
            id = itemId.toInt(),
            preco = preco,
            recarga = recarga,
            bateria = bateria,
            potencia = potencia,
            urlPhoto = urlPhoto,
            isFavorite = true
        )
    }

    fun saveIfNotExist(carro: Carro) : Boolean {
        var isSaved = false
        val car = findCarById(carro.id)
        if (car != null) {
            if (car.id == ID_WHEN_NO_CAR) {
                isSaved = save(carro)
            }
        }
        return isSaved
    }

    fun getAll() : List<Carro>{
        val dbHelper = CarsDbHelper(context)
        val db = dbHelper.readableDatabase
        val columns = arrayOf(BaseColumns._ID,
            COLUMN_NAME_CAR_ID,
            COLUMN_NAME_PRECO,
            COLUMN_NAME_BATERIA,
            COLUMN_NAME_POTENCIA,
            COLUMN_NAME_RECARGA,
            COLUMN_NAME_URL_PHOTO)

        val cursor = db.query(
            CarrosContract.CarEntry.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        val carro = mutableListOf<Carro>()
        with(cursor) {
            while (moveToNext()) {
                val itemId = getInt(getColumnIndexOrThrow(COLUMN_NAME_CAR_ID))
                Log.d("ID -> ", itemId.toString())
                val preco = getString(getColumnIndexOrThrow(COLUMN_NAME_PRECO))
                val bateria = getString(getColumnIndexOrThrow(COLUMN_NAME_BATERIA))
                val potencia = getString(getColumnIndexOrThrow(COLUMN_NAME_POTENCIA))
                val recarga = getString(getColumnIndexOrThrow(COLUMN_NAME_RECARGA))
                val urlPhoto = getString(getColumnIndexOrThrow(COLUMN_NAME_URL_PHOTO))
                carro.add(Carro(itemId, preco, bateria, potencia, recarga, urlPhoto, true))
            }
        }
        cursor.close()
        return carro
    }

    companion object {
        const val ID_WHEN_NO_CAR = 0
    }
}