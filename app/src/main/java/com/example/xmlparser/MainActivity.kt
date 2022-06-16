package com.example.xmlparser

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.lang.Exception
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {
   var tv1: TextView? = null
    @SuppressLint("SetTextI18n")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv1 = findViewById<View>(R.id.textView1) as TextView
        try {
            val `is` = assets.open("file.xml")
            val dbFactory = DocumentBuilderFactory.newInstance()
            val dBuilder = dbFactory.newDocumentBuilder()
            val doc = dBuilder.parse(`is`)
            val element = doc.documentElement
            element.normalize()
            val nList = doc.getElementsByTagName("employee")
            for (i in 0 until nList.length) {
                val node = nList.item(i)
                if (node.nodeType == Node.ELEMENT_NODE) {
                    val element2 = node as Element
                    tv1!!.text = """
                        ${tv1!!.text}
Name : ${getValue("name", element2)}

                        """.trimIndent()
                    tv1!!.text = """
                        ${tv1!!.text}Surname : ${
                        getValue(
                            "surname",
                            element2
                        )
                    }

                        """.trimIndent()
                    tv1!!.text = tv1!!.text.toString() + "-----------------------"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        private fun getValue(tag: String, element: Element): String {
            val nodeList = element.getElementsByTagName(tag).item(0).childNodes
            val node = nodeList.item(0)
            return node.nodeValue
        }
    }
}