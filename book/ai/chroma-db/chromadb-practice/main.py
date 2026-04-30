import chromadb

def main():
    print("Creating chromadb client")
    client = chromadb.Client()
    print("Created client: ", client)

    # client heartbeat
    heartbeat = client.heartbeat()
    print("heartbeat: ", heartbeat)
    
    # create new collection
    print("Creating collections")
    collection1 = client.create_collection("collection1")
    collection2 = client.create_collection("collection2")
    print("Collections created")

    # Get any one collection
    collection11 = client.get_collection("collection1")
    print("collection11: ", collection11)

    # get collection if exists or create new if doesn't exist
    collection3 = client.get_or_create_collection("collection3");

    # collection list
    collectionList = client.list_collections()
    print("Collections list: ", collectionList)

    # delete collection
    print("Deleting collection")
    client.delete_collection("collection3")
    print("Deleted collection")

    # collection list
    collectionList = client.list_collections()
    print("Collections list: ", collectionList)

    # Add embedding and documents
    print("Adding documents embeddings metadata data to collection ", collection1)
    collection1.add(
        documents=[
            "Welcome to AI ML",
            "Welcome to vector database",
            "Welcome to chromadb"
        ],
        metadatas=[
            {"source": "greeting", "language": "en"}, 
            {"source": "greeting", "language": "en"}, 
            {"source": "greeting", "language": "en"}
        ],
        ids=["1", "2", "3"],
        embeddings=[
            [0.1, 0.2, 0.3], 
            [0.4, 0.5, 0.6], 
            [0.7, 0.8, 0.9]
        ],
    )
    print("Data added to collection: ", collection1)
    collection1DocumentsCount = collection1.count()
    print("Collection1 documents count", collection1DocumentsCount)

    # Get collection details
    collection1Details = collection1.get()
    print("collection1 details", collection1Details)

    results = collection1.query(
        # query_embeddings=[[0.1, 0.2, 0.3]],
        query_embeddings=[[0.1, 0.2, 0.4]],
        n_results=3
    )
    print("result: ", results)

    # Reset client. This cannot be undone
    # client.reset()


if __name__ == "__main__":
    main()
