package com.example.memo

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var getText: ActivityResultLauncher<Intent>
    private val dataList = ArrayList<Data>()
    private lateinit var dataAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        dataAdapter = DataAdapter(dataList)

        Toast.makeText(this.applicationContext,"onCreate",Toast.LENGTH_SHORT).show()

        viewBinding.btnAdd.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            getText.launch(intent)
        }

        viewBinding.rvData.adapter = dataAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)

        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.containerFragement.id,HomeFragment())
            .commitAllowingStateLoss()

        viewBinding.navBottom.run {
            setOnItemSelectedListener{
                when(it.itemId){
                    R.id.menu_home ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragement.id,HomeFragment())
                            .commitAllowingStateLoss()
                    }
                    R.id.menu_setting ->{
                        supportFragmentManager
                            .beginTransaction()
                            .replace(viewBinding.containerFragement.id,SettingFragment())
                            .commitAllowingStateLoss()
                    }
                }
                true
            }
            selectedItemId= R.id.menu_home
        }

        getText = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){   // 문자열 받아오기
                result -> if(result.resultCode== RESULT_OK){
            val mString = result.data?.getStringExtra("data")
            Log.d(TAG,"onCreate: good To go: $mString")
            if (mString != null) {
                dataList.add(Data(R.drawable.trash, mString))
                dataAdapter.notifyDataSetChanged()
                Toast.makeText(this, "메모가 추가되었습니다.", Toast.LENGTH_SHORT).show()
            }
        }
        }
    }
    override fun onResume() { //
        super.onResume()
        Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {   // 정지 상태에 있을 떄
        super.onPause()
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show()
    }

}