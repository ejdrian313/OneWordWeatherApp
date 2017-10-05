package pl.kotliners.mystartapp

/**
 * Created by ejdrian on 05.10.17.
 */
class Response(val query: Query)
class Query(val count: Int, val created: String, val lang: String, val results: Results)
class Results(val channel: Channel)
class Channel(val item: Item)
class Item(val condition: Condition)
class Condition(val text: String)