package com.example.memo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private var memo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        Toast.makeText(this.applicationContext,"onCreate",Toast.LENGTH_SHORT).show()

        viewBinding.confirmButton.setOnClickListener {
            val intent = Intent(this,SecondActivity::class.java)
            intent.putExtra("data",viewBinding.editText.text.toString())
            startActivity(intent)
        }

    }
     override fun onResume() {
        super.onResume()
        if (memo.isNotEmpty()) {
            viewBinding.editText.setText(memo)
        }
         Toast.makeText(this,"onResume",Toast.LENGTH_SHORT).show()
    }
    override fun onPause() {
        super.onPause()
        memo = viewBinding.editText.text.toString()
        viewBinding.editText.text.clear()
        Toast.makeText(this,"onPause",Toast.LENGTH_SHORT).show()
    }

    override fun onRestart() {
        super.onRestart()
        showRestartDialog()
        Toast.makeText(this,"onRestart",Toast.LENGTH_SHORT).show()
    }

    private fun showRestartDialog() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("다시 작성하시겠습니까?")
        alertDialogBuilder.setPositiveButton("예"){dialog, which ->
            viewBinding.editText.setText(memo)
        }
        alertDialogBuilder.setNegativeButton("아니오", null)
        alertDialogBuilder.show()
    }
}
