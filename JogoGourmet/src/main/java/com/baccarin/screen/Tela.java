package com.baccarin.screen;

import com.baccarin.reg.Classificacao;
import com.baccarin.reg.Prato;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author baccarin
 */
public class Tela extends JFrame {

    private String titulo;
    public ArrayList<Prato> pratos = new ArrayList<Prato>();
    public JTextField txtCadPrato = new JTextField("Prato");

    public Tela(String titulo) {
        this.titulo = titulo;
        this.initObject();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(ArrayList<Prato> pratos) {
        this.pratos = pratos;
    }

    public void init() {
        JFrame frame = new JFrame();
        this.beginScreen(frame);
    }

    public void beginScreen(JFrame frame) {
        int resp = JOptionPane.showConfirmDialog(frame, "Pense em um prato que você gosta",
                this.titulo, JOptionPane.DEFAULT_OPTION);
        if (resp == 0) {
            this.questionScreen(frame);
        }
    }

    public void victory(JFrame frame) {
        JOptionPane.showMessageDialog(null, "Acertei de novo!");
        this.beginScreen(frame);
    }

    public int question(JFrame frame, Prato prato) {
        if (prato.getClassificacao() == null) {
            return this.nameQuestion(frame, prato);
        }
        return JOptionPane.showConfirmDialog(frame, String.format("O prato que você pensou foi %s",
                prato.getClassificacao().getClassificacao()),
                this.titulo, JOptionPane.YES_NO_OPTION);
    }

    public int nameQuestion(JFrame frame, Prato prato) {
        return JOptionPane.showConfirmDialog(frame, String.format("O prato que você pensou foi %s",
                prato.getNome()),
                this.titulo, JOptionPane.YES_NO_OPTION);
    }

    public void questionScreen(JFrame frame) {
        boolean confirmed = false, needSecondQuestion = true;
        for (Prato prato : pratos) {
            if (!confirmed) {
                needSecondQuestion = (prato.getClassificacao() != null);
                if (question(frame, prato) == JOptionPane.YES_OPTION) {
                    if (needSecondQuestion) {
                        if (nameQuestion(frame, prato) == JOptionPane.YES_OPTION) {
                            confirmed = true;
                            this.victory(frame);
                        } else {
                            this.screenCad(frame);
                        }
                    } else {
                        confirmed = true;
                        this.victory(frame);
                    }
                }
            } else {
                this.beginScreen(frame);
            }
        }
        if (!confirmed) {
            this.screenCad(frame);
        }
    }

    public void screenCad(JFrame frame) {
        String newPrato = JOptionPane.showInputDialog("Qual o prato que você pensou?");
        this.addNewPrato(newPrato);
        this.questionScreen(frame);
    }

    public void addNewPrato(String prato) {
        Prato newPrato = new Prato(prato);
        String newClassificacao = JOptionPane.showInputDialog(
                String.format("%s é ____ mas %s não", newPrato.getNome(), pratos.get(0).getNome()));
        newPrato.setClassificacao(new Classificacao(newClassificacao));
        this.pratos.add(newPrato);
    }

    public void initObject() {
        Prato lasanha = new Prato("Lasanha", (new Classificacao("massa")));
        Prato bolo = new Prato("Bolo de chocolate");
        this.pratos.add(lasanha);
        this.pratos.add(bolo);
    }

}