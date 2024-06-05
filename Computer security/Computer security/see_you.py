#!/usr/bin/python3

import hashlib
import string
import base64

from secret import flag

def encode(clear, key):
  enc = []
  for i in range(len(clear)):
    key_c = key[i % len(key)]
    enc_c = chr((ord(clear[i]) + ord(key_c)) % 128)
    enc.append(enc_c)
  return str(base64.urlsafe_b64encode("".join(enc).encode('ascii')), 'ascii')

'''
# For debug purpose
def decode(enc, key):
    dec = []
    enc = str(base64.urlsafe_b64decode(enc.encode('ascii')), 'ascii')
    for i in range(len(enc)):
        key_c = key[i % len(key)]
        dec_c = chr((128 + ord(enc[i]) - ord(key_c)) % 128)
        dec.append(dec_c)
    return "".join(dec)
 
assert(all(c in string.ascii_lowercase for c in flag))
assert(len(flag) == 8)
'''

hashed = hashlib.sha256(flag.encode("ascii")).hexdigest()[:8]

print("flag{%s_%s}" % (flag, hashed))

k1 = flag[0:4]
k2 = flag[4:8]

m = "See you later in the city center"
d = encode(m, k1)
c = encode(d, k2)

print("Message:", m)
print("Ciphertext:", c)


'''
m = "See you later in the city center"
c = "QSldSTQ7HkpIJj9cQBY3VUhbQ01HXD9VRBVYSkE6UWRQS0NHRVE3VUQrTDE="
'''
