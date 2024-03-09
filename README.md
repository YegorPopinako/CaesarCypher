
# **Ceasar Cypher**

1. **Encryption:** In the Caesar Cypher, each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet. For example, with a shift of 3, 'A' would be replaced by 'D', 'B' would become 'E', and so forth.

2. **Decryption:** Decryption is the reverse process of encryption. It involves converting encrypted data, known as cyphertext, back into its original plaintext form.

## **How Does it Work?**
Running the application from the console itself will look like `java -jar CaesarCypher-1.0-SNAPSHOT.jar command filePath key`

**command** - three available options: [`ENCRYPT`, `DECRYPT`, `BRUTE_FORCE`]

**filepath** - absolute (full) path to the file to be encoded.

**key** - an integer, the key for shifting the alphabet.

> [!IMPORTANT]
> In case of transfer `ENCRYPT/DECRYPT` `key` is mandatory.

## Brute force
> [!WARNING]
> The Brute Force algorithm does not keep the letters' case when decrypting, the decrypted text will only be in lower case

* The brute force algorithm iterates though the alphabet while decrypting and suggests 6 most passing keys, decrypts and writes to a file decrypted text with mentioning the `key` value

## Example of brute force:
### Encrypted file:

![image](https://github.com/YegorPopinako/CaesarCypher/assets/89963153/65d8b361-7f0d-4576-b3e7-6675fa1acff4)

Passing `java -jar CaesarCypher-1.0-SNAPSHOT.jar BRUTE_FORCE "C:\Users\yegor\OneDrive\Desktop\src.txt"`

### Result: 
![image](https://github.com/YegorPopinako/CaesarCypher/assets/89963153/726e861d-1243-4cf2-95cf-d54886a2c061)
