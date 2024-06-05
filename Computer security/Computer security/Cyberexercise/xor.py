import base64

def xor_decrypt_malleable(ciphertext):
    decrypted = bytearray()
    for i in range(len(ciphertext)):
        decrypted.append(ciphertext[i] ^ ciphertext[i % len(ciphertext)])
    return bytes(decrypted)

# Stringa criptata in Base64
encrypted_message_base64 = "Aw0SHh4ZQys6UEYmEVFDJghVHxVWVRFIVj4VSRc+BxFRFQ4="

# Decodifica Base64
decoded_message = base64.b64decode(encrypted_message_base64)

# Decifratura XOR malleabile
decrypted_message = xor_decrypt_malleable(decoded_message)

# Stampa il risultato
print(decrypted_message.decode('utf-8'))
