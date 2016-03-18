package main

import models.Post
import org.bson.types.ObjectId

object Main {

  def main(args: Array[String]) {
    // 全件取得
    Post.findAll.foreach(println)

    // 件数
    println(Post.count)

    // IDで1件取得
    val objectId = new ObjectId("56ab1fcf800a9404e1854a70")
    Post.findById(objectId).map(println)

    // タイトルで検索
    val title = "タイトル2"
    Post.findByTitle(title).foreach(println)

  }

}
