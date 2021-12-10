import { toBase64String } from '@angular/compiler/src/output/source_map';
import { Component, NgZone, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Facture } from '../Models/Facture';
import { FactureService } from '../Services/facture.service';
declare const annyang: any;
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  constructor(
    private ngZone: NgZone,
    private router: Router,
    private FactServ: FactureService
  ) {  
    localStorage.setItem('id', '1');
  }
  voiceActiveSectionDisabled: boolean = true;
  voiceActiveSectionError: boolean = false;
  voiceActiveSectionSuccess: boolean = false;
  voiceActiveSectionListening: boolean = false;
  voiceText: any;
  fact: Facture = new Facture();

  ngOnInit(): void {
    this.fact.subtotal = 0;
    this.fact.tax = 10;
    this.fact.shipping = 10;
    this.fact.total = 20;
    this.fact.codeQrAuthentif = 'QR';
    const id = this.FactServ.getCountClientFacture(
      localStorage.getItem('id')
    ).subscribe((d) => {
      if (d == 0) {
        this.FactServ.PostFacture(
          localStorage.getItem('id'),
          this.fact
        ).subscribe((e) => {
          console.log(e.byteLength);
          let TYPED_ARRAY = new Uint8Array(e);
          const STRING_CHAR = TYPED_ARRAY.reduce((data, byte) => {
            return data + String.fromCharCode(byte);
          }, '');
          let base64String = btoa(STRING_CHAR);
          console.log(base64String);
          localStorage.setItem('image', base64String);
        });
        localStorage.setItem('id', this.fact.id.toString());
        localStorage.setItem;
      }
    });
  }
  image: any;
  initializeVoiceRecognitionCallback(): void {
    annyang.addCallback('error', (err: any) => {
      if (err.error === 'network') {
        this.voiceText = 'Internet is require';
        annyang.abort();
        this.ngZone.run(() => (this.voiceActiveSectionSuccess = true));
      } else if (this.voiceText === undefined) {
        this.ngZone.run(() => (this.voiceActiveSectionError = true));
        annyang.abort();
      }
    });

    annyang.addCallback('soundstart', (res: any) => {
      this.ngZone.run(() => (this.voiceActiveSectionListening = true));
    });

    annyang.addCallback('end', () => {
      if (this.voiceText === undefined) {
        this.ngZone.run(() => (this.voiceActiveSectionError = true));
        annyang.abort();
      }
    });

    annyang.addCallback('result', (userSaid: any) => {
      this.ngZone.run(() => (this.voiceActiveSectionError = false));
      let queryText: any = userSaid[0];
      annyang.abort();
      this.voiceText = queryText;
      console.log(queryText);
      // route to the given Voice
      this.router.navigateByUrl('/client/' + this.voiceText);
      this.ngZone.run(() => (this.voiceActiveSectionListening = false));
      this.ngZone.run(() => (this.voiceActiveSectionSuccess = true));
    });
  }

  startVoiceRecognition(): void {
    this.voiceActiveSectionDisabled = false;
    this.voiceActiveSectionError = false;
    this.voiceActiveSectionSuccess = false;
    this.voiceText = undefined;

    if (annyang) {
      let commands = {
        'demo-annyang': () => {},
      };

      annyang.addCommands(commands);

      this.initializeVoiceRecognitionCallback();

      annyang.start({ autoRestart: false });
    }
  }

  closeVoiceRecognition(): void {
    this.voiceActiveSectionDisabled = true;
    this.voiceActiveSectionError = false;
    this.voiceActiveSectionSuccess = false;
    this.voiceActiveSectionListening = false;
    this.voiceText = undefined;

    if (annyang) {
      annyang.abort();
    }
  }
}
