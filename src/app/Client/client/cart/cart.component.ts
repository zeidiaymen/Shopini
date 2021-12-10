import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Delivery } from '../Models/delivery';
import { Facture } from '../Models/Facture';
import { ProductInOrder } from '../Models/ProductInORder';
import jsPDF from 'jspdf';
import * as XLSX from 'xlsx';
import 'jspdf-autotable';
import { DeliveryServService } from '../Services/delivery-serv.service';
import { FactureService } from '../Services/facture.service';
import { ProdInOrderService } from '../Services/prod-in-order.service';
import { toBase64String } from '@angular/compiler/src/output/source_map';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  // QR: Byte[] =  localStorage.getItem('qr');
  imgs = localStorage.getItem('image');
  img!: string;
  im!: SafeUrl;
  delivery = new Delivery();
  facture = new Facture();
  constructor(
    private serv: FactureService,
    private domSanitizer: DomSanitizer,
    private router: Router,
    private dserv: DeliveryServService,
    private prodIn: ProdInOrderService
  ) {}
  ProdInOrder: ProductInOrder[] = [];
  ngOnInit(): void {
    console.log(this.imgs);
    this.img = 'data:image/jpeg;base64,' + this.imgs;
    this.im = this.domSanitizer.bypassSecurityTrustUrl(this.img);
    this.serv
      .getFactureByID((localStorage.getItem('id')))
      .subscribe((e) => {
        this.facture = e;
        console.log(this.facture)

      });
    this.delivery.address = 'Bardo';
    this.delivery.cell_phone = '216 xx xx xx';
    this.delivery.weight = 'under5KG';
    this.prodIn.getAllProdsInORder(localStorage.getItem('id')).subscribe((e) => {
      this.ProdInOrder = e;
    });
  }
  tog = true;
  scan() {
    this.tog = false;
  }
  hr = false;
  data: any;
  scanQR(ecent: String) {
    this.data = ecent;
    console.log(ecent);

    this.serv.getFactureByQR(ecent).subscribe((b) => {
      console.log(b);
      this.serv.PaypalPayement(b.id).subscribe((e) => {
        window.open(e);

        this.dserv
          .postDelivery(this.delivery, (localStorage.getItem('id')))
          .subscribe();
          this.dserv
          .postDelivery(this.delivery, (localStorage.getItem('id')))
          .subscribe();
          this.prodIn.deleteAllProds(this.facture.id).subscribe();

      });
    });
  }
  
  payement() {
    this.serv.PaypalPayement(this.facture.id).subscribe((e) => {
      window.open(e);

      this.dserv
        .postDelivery(this.delivery, (localStorage.getItem('id')))
        .subscribe();
      this.prodIn.deleteAllProds(this.facture.id).subscribe();
    });
  }

  deleteProdById(id: any) {
    this.prodIn.deleteProdInOrder(id).subscribe((e) => {
      this.serv
        .getFactureByID((localStorage.getItem('id')))
        .subscribe((e) => {
          this.facture = e;
        });
      this.prodIn.getAllProdsInORder(localStorage.getItem('id')).subscribe((e) => {
        this.ProdInOrder = e;
      });
    });
  }
  id!: number;
  active!: boolean;
  codeQrAuthentif!: String;
  dateFacture!: Date;
  description!: String;
  shipping!: number;
  subtotal!: number;
  tax!: number;
  total!: number;
  head = [['Id','dateFacture','shipping','subtotal','tax','total']];

  pdfDow() {
    console.log(this.facture)
    var doc = new jsPDF();
    doc.setFontSize(15);
    doc.text('Invoice :', 15, 10);
    doc.setFontSize(3);
    doc.setTextColor(100);
var x =[];
x.push (Object.values (this.facture));   
    (doc as any).autoTable({
      head:this.head,
      body: x,
      theme: 'plain',
     
    })
   
    // Open PDF document in new tab

    // Download PDF document  
    doc.save('Invoice.pdf');
  }
  pdfVis() {
    console.log(this.facture)
    var doc = new jsPDF();
    doc.setFontSize(17);
    doc.text('Invoice :', 15, 10);
    doc.setFontSize(10);
    doc.setTextColor(130);
var x =[];
x.push (Object.values (this.facture));   
    (doc as any).autoTable({
      head:this.head,
      body: x,
      theme: 'plain',
     
    })
    var img ='https://www.shopinilens.com/wp-content/uploads/2020/07/Lens-Shopini-logo-03-e1603442060862.png';
    doc.addImage('../../../assets/1.jpg', 'png', 0, 50, 250, 200)

    // Open PDF document in new tab
    doc.output('dataurlnewwindow')

    // Download PDF document  
  }

  Excel()
  {
    let element = document.getElementById('excel-table'); 
    const ws: XLSX.WorkSheet =XLSX.utils.table_to_sheet(element);

    /* generate workbook and add the worksheet */
    const wb: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');

    /* save to file */
    XLSX.writeFile(wb,"Facture"+'.xlsx' );
  }
}
