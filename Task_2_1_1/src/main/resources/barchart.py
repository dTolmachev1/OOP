import matplotlib.pyplot as plt

algorithm = ['Sequential','Parallel stream','Single thread','2 threads','3 threads','4 threads','5 threads','6 threads','7 threads','8 threads','9 threads','10 threads','11 threads','12 threads','13 threads','14 threads','15 threads','16 threads']
execution_time = [100,8,151,72,61,46,41,41,45,44,46,49,52,54,54,54,57,53]

plt.bar(algorithm, execution_time)
plt.title('Execution time for different level of concurrency', fontsize=14)
plt.xlabel('Algorithm', fontsize=14)
plt.ylabel('Execution time', fontsize=14)
plt.grid(True)
plt.yticks(range(0,170,10))
plt.savefig('barchart.pdf')