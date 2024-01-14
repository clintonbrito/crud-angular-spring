import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrl: './error-dialog.component.scss'
})
export class ErrorDialogComponent implements OnInit {
  // O `implements OnInit` é opcional nesse caso, só não sei porquê

  constructor(@Inject(MAT_DIALOG_DATA) public data: string) {}

  ngOnInit(): void {

  }
}
