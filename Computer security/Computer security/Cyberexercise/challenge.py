from Crypto.Util.number import getPrime, bytes_to_long

FLAG = "leoctf{REDACTED_THIS_IS_NOT_THE_FLAG_DO_NOT_SUBMIT_THIS_TO_THE_PLATFORM_PLEASE}".encode()
p, q = getPrime(1024), getPrime(1024)
n = p*q
e = n-p-q+4

ct = pow(bytes_to_long(FLAG), e, n)

print("n =", n)
print("ct =", ct)