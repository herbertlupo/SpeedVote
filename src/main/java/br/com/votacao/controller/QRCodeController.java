package br.com.votacao.controller;

import br.com.votacao.service.QRCodeService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/qrcode")
public class QRCodeController {

    @Autowired
    private QRCodeService qrCodeService;

    @GetMapping("/cadastro")
    public ResponseEntity<byte[]> gerarQrCodeCadastro(@RequestParam Long sessaoId) throws WriterException, IOException {
        // Substituir pela URL real do frontend com o parâmetro da sessão
        String url = "https://site.com/cadastro?sessaoId=" + sessaoId;

        byte[] qrCode = qrCodeService.generateQRCode(url, 300, 300);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return ResponseEntity.ok()
                .headers(headers)
                .body(qrCode);
    }
}
