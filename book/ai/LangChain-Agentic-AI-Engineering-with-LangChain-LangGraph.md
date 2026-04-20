# Udemy - LangChain- Agentic AI Engineering with LangChain & LangGraph
* Develop LLM powered agents with langchain & LangGraph

## LLM Applications types
* Agents
* Retrieval Augmentation Generation (RAG)

# What is LangChain?
* Open source framework that simplifies the process of building LLM powered applications. Like  to Spring in Java
* Provides tools and abstraction that make it easier to create complex LLM powered applications
* Widely adopted. Specially by developers who can build LLM applications without understanding how machine leaning works, how to train models. But use models as black box
* Gives library to connect with LLM and switch between LLMs with simple configuration without changing business logic

## LangChain Prompts
* Help prompts management, optimization, serialization
* Create prompt template. Inject values to prompt at run time. Like `String.format`

## Document loaders
* Helps us to load different data sources like Notion, pdf files, notepads, email etc

---
# What is an agent?
* Software system that use LLM as reasing engine to decide what actions to take and execute those actions
* In chain - sequence of steps are hard coded. In agents - Dynamically determine which tools or steps need to be taken to solve specific task. LLM in agent decides what to do next

---
# RAG Embeddings Vector Databases and Retrieval
* RAG - Retrieval Augmentation Generation
* Prompt + Augment relevant context data - then LLM is able to answer

## If we have large documents 
### Solution 1
* Send entire document text to LLM as prompt then following problems happen
* Hard Token Limit
* Needle in the Heystack
* Cost
* Latency

### Solution 2
* Split original document into smaller chunks (This chunking process is naive and complex)
* Instead of sending entire book, send only specific paragraph or some more
* Drawback
	* Preproecssing to chunk large document. How do we chunk?
	* How to identify relevant chunk for data?
	
## Introduction to Vector Databases
* Embeddings
* Vector stores
	* Licensed: Pinecone, Qdrant
	* Open source: ChromaDB, Milvus, Weaviate, Pgvector
* RetrievalQA Chain
* LangChain document loaders
* LangChain text splitters

## Basic RAG Pipeline
![picture](imgs/basic-rag-pipeline.png)

### 🔹 Data Indexing
1. **Documents** → raw input files (PDFs, text, etc.)  
2. **Data Loading** → bring documents into the system  
3. **Data Splitting** → break into smaller **text chunks**  
4. **Data Embedding** → convert chunks into **vector embeddings**  
5. **Data Storing** → save embeddings in a **Vector Database (Vector DB)**  

### 🔹 Data Retrieval & Generation
1. **User Query** → input question  
2. **Vector Embedding** → convert query into embedding  
3. **Vector DB** → search for similar embeddings  
4. **Top-K Chunks** → retrieve most relevant text chunks  
5. **LLM (Large Language Model)** → combine retrieved chunks with query  
6. **Response** → generate final answer  

This cleaned pipeline shows the **core RAG workflow**:  
- **Indexing** prepares knowledge for retrieval.  
- **Retrieval + Generation** ensures the LLM produces grounded, context-aware answers.  

## pinecone
* https://www.pinecone.io/ 
* Create index. Give this index name in langchain using key `INDEX_NAME`
* Key to use langchain to integrate with pinecone - `PINECONE_API_KEY`

---
# Prompt engineering theory
* Language Model (LM) in simple words
	* What should be the next words == Words with highest probability
* Large Language Model (LLM)
	* Language Model that is trained on huge amount of data. Then it becomes very good in calculating probability (Discussed above in LM)

## What is prompt
* Specific input or task given to LLM to generate desired output
* Different components of prompt - `Instructions`, `Context`, `Input Data`, `Output Indicator`

### Instructions
* What task it need to perform. Like text summary, translation, classification
* Instructions set the stage for AI response

### Context
* Additional information to fine tune Instruction
* For some tasks context may not be necessary. But for some task context may be very important

### Input Data
* Information AI model process to complete the task

## Zero shot prompting
* No examples are given
* Depend on model pre-training data to generate output

## One shot prompt
* Model is given 1 example
* With n example where n == 1

## Few shot prompt
* Model is given few examples (More than one)
* This is helpful where LLM has limited data available for given task
* With n examples where n > 1
---
# Chain of Thought COT
* Google introduced this
* Aims to improve reasining ability of LLM
* Helps models to divide multi-step problems to intermediate steps. This makes models to solve complext problems

---
# ReAct prompting
* Integrates models with `Reasoning` and `Acting` capabilities
* This allows `dynamic reasoning`, `external systems interaction` to solve complex task
* ReAct = COT + Actions

---
# Prompt engineering quick tips
* Give clear `context`
* Clear and non-ambiguous task
* Iterations

---
# Agents in production environment

## LLM application development
* GenAI Applications - Simple LLM applications. Very simple
* VectorStores (RAG)
* Agents
* Agents + VectorStores

---
# What is an Agent
* An AI agent is an autonomous software system that uses Artificial Intelligence—often Large Language Models (LLMs)—to perceive its environment, reason, plan, and take actions to achieve specific goals with minimal human oversight. Unlike chatbots, which simply generate text, agents use tools (APIs, web browsers, software, databases) to complete multi-step tasks. 

## Key Characteristics of AI Agents
* Autonomy: Agents act on their own, making decisions to achieve a goal rather than waiting for constant, step-by-step human instructions.
* Goal-Oriented: They are given an objective and determine the best actions to achieve it.
* Tool Use: They utilize external tools, such as web searches, databases, or APIs, to perform actions, such as sending emails or booking flights.
* Reasoning and Planning: Agents can break down complex tasks into manageable subtasks, analyze data, and learn from outcomes.
* Memory: They retain information from past interactions to inform future actions. 

## How AI Agents Work
* AI agents often use LLMs as their `brain` to understand input and determine actions. They operate in a loop: 
* Perceive: They read the environment or user input.
* Think: They reason and create a plan.
* Act: They execute steps using tools.
* Adapt: They analyze the outcome and refine their approach. 

## Examples of AI Agents
* Virtual Assistants: Siri, Alexa, or Google Assistant, which can interact with other apps to set reminders or manage smart home devices.
* Customer Service Agents: Bots that resolve queries, update records, and process transactions in real time.
* Data Analysis Agents: Agents that monitor marketing performance, automatically adjusting budgets across platforms.
* Development Agents: Systems that can automatically write, debug, and publish code. 

## Conclusion
AI agents represent a shift from passive AI systems to proactive digital teammates that improve efficiency and productivity

---
# GenAI applications UI UX
* Called Generatinve UI (or) GenUI

## Tools to build GenUI
* CopilotKit

## CopilotKIt
* Open source
* Build beautiful and natural UI UX

---
# Confidence in AI Results
* Real metric to use AI products - `CAIR` - `Confidenct In AI Results`. Measures with `CAIR Ratio` between zero(0) to one(1)
* CAIR formula = (value of success) / (risk of error * effor to correct)
* value - benefit to user when AI succeed
* risk - consequences when AI make an error like is it ok, dangerous like self driving cars
* correction - effort required to fix AI mistake
* High CAIR == High usage
* Low CAIR == failed product

---
# FOMO
* Fear Of Missing Out

---
# Tips for LLM application development
* Prompt management
* Monitoring - What is the latency. What is the cost of each request. How much we are paying to LLM vendors? 
* Debugging
* Evaluation

## LLM Ops - LLM operations
* LangSmith
* Pezzo - Helps in prompt management, tracing, monitoring LLM operations

## Resources for updated information in AI world
* Follow LangChain blogs
* Twitter - reading about GenAI, researches, new applications, new use cases, new ways to optimize things

---
# LangGraph
* Using langchain we can build RAG applications, Agents etc
* But we have limitations when we build agents within langchain
* Langchain does not support cycles. Means we cannot reexecute the chain if something is not correct
* LangGraph gives freedom to add new chains and complexity to agents
* We can implement `cycles` in LangGraph. Useful when building very complex agents. This concept is called `Flow Engineering`. Developer develops the flow and integrate LLM, then LLM decides which flow to go next or go to start over again. Cycles gives lot of freedome

# Simple design of LangGraph Agent is for loop
* First introduced ReAct paper
![picture]() -> Refer pic in phone and draw image and put here

---
# Graph and State Machine

## Graph
* Mathematical object with relationships
* Consists nodes/verices and edges

## State Machine
* Model of computation where it consists of states and transition between those states
* Define different states and rules for tranision between them. Then states can manage complex conditions in software systems
* State machines are represented as graphs where states as nodes, transition as edges

## LangGrpah
* Powerful library built on top of LangChain
* Using langgraph we design our flows using nodes and edges. Build powerful agentic applications

---
# LangGraph core components
* Nodes
* Edges
* Conditional Edges

## Nodes
* Python functions

## Edges and conditional edges
* Connects the nodes within graph execution
* Conditional edges help to make decisions. wherther to go to node A, node B, this is dynamic and very flexible
* Built-in langgraph nodes - `__start__`, `__end__`
* `__start__` entry point for graph execution. `__end__` last node that is going to be executed

## Components of LangGraph
* Cyclic Graph
* Human In The Loop
* Persistence - State management. Help us persist state of the graph