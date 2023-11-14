package com.example.application.views.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("adivinhacao")
public class JogoAdivinhacaoPalavraVaadin extends VerticalLayout {

    private String palavraAdivinhar = "Java";
    private char[] letrasAdivinhadas;
    private int tentativas = 0;

    private TextField letraField = new TextField("Digite uma letra");
    private Button tentarButton = new Button("Tentar");
    private Button dicaButton = new Button("Dica");

    public JogoAdivinhacaoPalavraVaadin() {
        inicializarJogo();

        tentarButton.addClickListener(event -> tentarAdivinhar());
        dicaButton.addClickListener(event -> darDica());

        add(letraField, tentarButton, dicaButton);
    }

    private void inicializarJogo() {
        letrasAdivinhadas = new char[palavraAdivinhar.length()];
        for (int i = 0; i < letrasAdivinhadas.length; i++) {
            letrasAdivinhadas[i] = '_';
        }
    }

    private void tentarAdivinhar() {
        char tentativa = letraField.getValue().toLowerCase().charAt(0);
        boolean letraCorreta = false;

        for (int i = 0; i < palavraAdivinhar.length(); i++) {
            if (Character.toLowerCase(palavraAdivinhar.charAt(i)) == tentativa) {
                letrasAdivinhadas[i] = palavraAdivinhar.charAt(i);
                letraCorreta = true;
            }
        }

        tentativas++;

        // Verifica se todas as letras foram adivinhadas
        boolean palavraAdivinhada = true;
        for (char letra : letrasAdivinhadas) {
            if (letra == '_') {
                palavraAdivinhada = false;
                break;
            }
        }

        if (letraCorreta) {
            Notification.show("Letra correta! Palavra: " + String.valueOf(letrasAdivinhadas));
        } else {
            Notification.show("Letra incorreta. Tente novamente. Palavra: " + String.valueOf(letrasAdivinhadas));
        }

        if (palavraAdivinhada) {
            Notification.show("Parabéns! Você adivinhou a palavra \"" + palavraAdivinhar + "\" em " + tentativas + " tentativas.");
            reiniciarJogo();
        }
    }

    private void darDica() {
        // Lógica para fornecer uma dica, por exemplo, exibir uma letra da palavra.
        char letraDica = obterLetraDica();
        Notification.show("Dica: " + letraDica);
    }

    private char obterLetraDica() {
        for (int i = 0; i < palavraAdivinhar.length(); i++) {
            if (letrasAdivinhadas[i] == '_') {
                return palavraAdivinhar.charAt(i);
            }
        }
        return palavraAdivinhar.charAt(0);
    }

    private void reiniciarJogo() {
        tentativas = 0;
        inicializarJogo();
    }
}

