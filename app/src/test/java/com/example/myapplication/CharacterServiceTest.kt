package com.example.myapplication

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CharacterServiceTest {
//
//    private lateinit var service: CharacterService
//    private lateinit var mockWebServer: MockWebServer
//
//    @Before
//    fun setup() {
//        mockWebServer = MockWebServer()
//        service = Retrofit.Builder()
//            .baseUrl(mockWebServer.url("/"))
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(CharacterService::class.java)
//    }
//
//    @After
//    fun teardown() {
//        mockWebServer.shutdown()
//    }
//
//    @Test
//    fun `getCharacter should return expected character data`() {
//        // Given
//        val characterId = 1
//        val characterJson = """
//        {
//            "name": "Rick Sanchez",
//            "status": "Alive",
//            "species": "Human",
//            "origin": {
//                "name": "Earth (C-137)"
//            }
//        }
//        """
//        mockWebServer.enqueue(
//            MockResponse()
//                .setResponseCode(200)
//                .setBody(characterJson)
//        )
//
//        // When
//        val call = service.getCharacter(characterId)
//        val response = call.execute()
//
//        // Then
//        assertEquals(200, response.code())
//        assertTrue(response.isSuccessful)
//        val character = response.body()
//        assertEquals("Rick Sanchez", character?.name)
//        assertEquals("Alive", character?.status)
//        assertEquals("Human", character?.species)
//        assertEquals("Earth (C-137)", character?.origin?.name)
//    }
}
