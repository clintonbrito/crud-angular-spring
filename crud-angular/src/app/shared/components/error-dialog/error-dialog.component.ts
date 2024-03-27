import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

@Component({
    selector: 'app-error-dialog',
    templateUrl: './error-dialog.component.html',
    styleUrl: './error-dialog.component.scss',
    standalone: true,
    imports: [MatDialogTitle, MatDialogContent, MatDialogActions, MatButtonModule, MatDialogClose]
})
export class ErrorDialogComponent implements OnInit {
  // O `implements OnInit` é opcional nesse caso, só não sei porquê

  constructor(@Inject(MAT_DIALOG_DATA) public data: string) {}

  ngOnInit(): void {

  }
}
