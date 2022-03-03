# java-test-sigabem-cd2tec

Implementar para empresa de transporte de cargas SigaBem o endpoint para o cálculo do preço do frete:

Você deve calcular o valor total do frete e a data prevista da entrega.

Considerar regras para calcular o valor do frete:

1. CEPs com DDDs iguais tem 50% de desconto no valor do frete e entrega prevista de 1 dia
2. CEPs de estados iguais tem 75% de desconto no valor do frete e entrega prevista de 3 dias
3. CEPs de estados diferentes não deve ser aplicado o desconto no valor do frete e entrega prevista de 10 dias
4. O valor do frete é cobrado pelo peso da encomenda, o valor para cada KG é R$1,00
Seu input de entrada deve ser “peso”, “cepOrigem”, “cepDestino” e “nomeDestinatario“
