import chromadb

def main():
    print("Hello from chromadb-practice!")
    client = chromadb.Client()
    
    collection = client.create_collection("my_collection")
    print("Collection created:", collection)

    collection.add(
        documents=["Hello world", "Hello chromadb", "Hello python"],
        ids=["1", "2", "3"],
        metadatas=[{"source": "greeting"}, {"source": "greeting"}, {"source": "greeting"}],
        embeddings=[[0.1, 0.2, 0.3], [0.4, 0.5, 0.6], [0.7, 0.8, 0.9]],
    )

    results = collection.query(
        query_embeddings=[[0.1, 0.2, 0.3]],
        n_results=2,
    )
    print(results)


if __name__ == "__main__":
    main()
