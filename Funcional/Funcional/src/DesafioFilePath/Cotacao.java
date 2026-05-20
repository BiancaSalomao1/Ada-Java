package DesafioFilePath;


import java.math.BigDecimal;
import java.time.LocalDate;

        public class Cotacao {

            private LocalDate data;
            private BigDecimal valorCompra;
            private BigDecimal valorVenda;

            public Cotacao(LocalDate data, BigDecimal valorCompra, BigDecimal valorVenda) {
                this.data = data;
                this.valorCompra = valorCompra;
                this.valorVenda = valorVenda;
            }

            public LocalDate getData() {
                return data;
            }

            public BigDecimal getValorCompra() {
                return valorCompra;
            }

            public BigDecimal getValorVenda() {
                return valorVenda;
            }
        }