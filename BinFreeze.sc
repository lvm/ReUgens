MagFreeze {

  *ar {
    |in, bufsize=2048, amp=0.1, freq, freeze|
    var chain, chain_r, thr;
    chain = FFT(LocalBuf(bufsize), in[0]);
    chain_r = FFT(LocalBuf(bufsize), in[1]);
    thr = FSinOsc.kr(freq).clip(0.1,0.6);
    chain = PV_MagFreeze(chain, thr);
    chain_r = PV_MagFreeze(chain_r, thr);

    ^(IFFT(chain).dup * amp * freeze);
  }

}

/*
  @author moxus
  https://github.com/moxuse/tidal-lazy/blob/master/start_superdirt.scd
*/

BinFreeze {

  *ar { |in, bufsize=2048, freq, freeze=0|
    var chain, chain_r, thr;

    chain = FFT(LocalBuf(bufsize), in[0]);
    chain_r = FFT(LocalBuf(bufsize), in[1]);
    thr = FSinOsc.kr(freq).clip(0.1,0.6);
    chain = PV_MagFreeze(chain, thr > 0.5);
    chain_r = PV_MagFreeze(chain_r, thr > 0.5);

    ^((in * (1.0 - freeze)) + (freeze * [IFFT(chain), IFFT(chain_r)]));
  }

}