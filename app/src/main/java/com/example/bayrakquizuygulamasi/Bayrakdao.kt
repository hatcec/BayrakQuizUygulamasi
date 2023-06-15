package com.example.bayrakquizuygulamasi

class Bayrakdao {
    fun rastgele5BayrakSorusuGetir(vt:VeriTabaniYardimcisi):ArrayList<Bayraklar>{
        val bayrakListesi=ArrayList<Bayraklar>()
        val db=vt.writableDatabase
        val sorgu=db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)
        while(sorgu.moveToNext()){
            val bayrak=Bayraklar(sorgu.getInt(sorgu.getColumnIndex("bayrak_id"))
                ,sorgu.getString(sorgu.getColumnIndex("bayrak_ad"))
                ,sorgu.getString(sorgu.getColumnIndex("bayrak_resim")))
            bayrakListesi.add(bayrak)
        }
        return bayrakListesi
    }
    fun rastgele3YanlisSecenekGetir(vt:VeriTabaniYardimcisi, bayrak_id:Int):ArrayList<Bayraklar>{
        val bayrakListesi=ArrayList<Bayraklar>()
        val db=vt.writableDatabase
        val sorgu=db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id!=$bayrak_id ORDER BY RANDOM() LIMIT 3",null)
        while(sorgu.moveToNext()){
            val bayrak=Bayraklar(sorgu.getInt(sorgu.getColumnIndex("bayrak_id"))
                ,sorgu.getString(sorgu.getColumnIndex("bayrak_ad"))
                ,sorgu.getString(sorgu.getColumnIndex("bayrak_resim")))
            bayrakListesi.add(bayrak)
        }
        return bayrakListesi
    }
}